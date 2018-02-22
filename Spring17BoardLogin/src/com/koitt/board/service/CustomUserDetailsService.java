package com.koitt.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koitt.board.model.Authority;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

/*
 * 각 사용자마디 권한을 설정하기 위해 아래와 같은 클래스를 작성한다.
 * 스프링은 아래 클래스를 객체화해서 사용하게 된다.
 * (applicationContext.xml user-servlet-ref속성 참고)
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UsersService service;

    @Override
    public UserDetails loadUserByUsername(String email) 
    		throws UsernameNotFoundException {
        
        try {
        	/*
        	 * 로그인할 떼 크라이언트로부터 전달받은 아잍(email) 값을 넘겨 받아
        	 * 해당 사용자의 정보를 불러온다.
        	 */
            Users users = service.detailByEmail(email);
            
            /*
             * 해당 사용자가 존재하지 않으면
             * 이미 정의된 UsernameNotFoundException을 이용하여
             * 예외를 생성해서 던져주면 스프링이 알아서 예외처리를 한게된다.
             */
            if (users == null) {
                throw new UsernameNotFoundException("해당 사용자를 찾지 못했습니다.");
            }
            
            /*
             * username
             * password
             * enabled
             * accountNonExpired(완료되지 않은 계정)
             * credentialsNonExpired
             * accountNonLocked
             * authorities
             */
            return new User(users.getEmail(), //하나라도 false이면 로그인이 안됨
            		users.getPassword(), 
            		true,
            		true, 
            		true, 
            		true, 
            		this.getGrantedAuthorities(users));//athorities(권한을 인정해주는 메소드)
            
        } catch (UsersException e) {
            System.out.println(e.getMessage());
        }
       
        return null;
    }
    //사용자에게 권한을 부여하기위해 권한 목록을 리턴하는 메소드
    private List<GrantedAuthority> getGrantedAuthorities (Users users) {
    	
    	//최종적으로 스프링에게 전달할 리스트(리스트 타입은 GrantedAuthorities )
    	List<GrantedAuthority> auths = new ArrayList<>();
    	
    	/*
    	 * 데이터베이스에 저장되어 있던 해당 사용자의 권한을 하나씩 끄집어 내어
    	 * GrantedAuthorities 리스트에 추가해주는 작업
    	 * 스프링 3번전에서는 "ROLE_" 이라는 문자열을 추가 안해도 되지만
    	 * 스프링 4번전 이후부터는 "ROLE_" 이라는 문자열을 권한이름 앞에 붙여줘야 정상 작동합니다. 
    								getAuthorities()-->join 해야하는 이유 */		
    	for(Authority item : users.getAuthorities()) {
    		auths.add(new SimpleGrantedAuthority("ROLE_" + item.getName()));
    	}
    	
    	System.out.println(users.getEmail() + "사용자의 권한: " + auths  );
    	
        return null;
    }

}
