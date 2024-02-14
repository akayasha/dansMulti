package com.test.dansmultipro.test.dansmultipro.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.dansmultipro.test.dansmultipro.model.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDetailsImpl extends User implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;



    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(
                user.getUsername(),
                user.getPassword());
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if(StringUtils.hasText(roles)){
//            String[] splits = roles.replaceAll(" ", "").split(",");
//            for(String string : splits){
//                authorities.add(new SimpleGrantedAuthority(string));
//            }
//        }
//        return authorities;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
