package inventoryManagementApplication.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import inventoryManagementApplication.model.Role;
import inventoryManagementApplication.model.User;

public class MyUserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	private Boolean enabled;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;

	public MyUserPrincipal(final User _user) {
		this.user = _user;
		this.enabled = true;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
	}

	public MyUserPrincipal() {
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return mapRolesToAuthorities(user.getRoles());
	}

	private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [user=" + user + "]";
	}
}
