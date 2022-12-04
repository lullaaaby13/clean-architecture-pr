package me.lullaby.tradlinxbackend.application.authenticate

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class AuthorizedUser(val userId: Long): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.createAuthorityList()
    override fun getUsername(): String = userId.toString()
    override fun getPassword(): String = ""
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean  = true
    override fun isEnabled(): Boolean  = true
}
