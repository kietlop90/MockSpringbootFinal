package com.mock.api.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

/**
 * Authorization utility class for determine whether access is granted for a specific authentication
 */
public class AuthorizationUtil {
    /**
     * Determines if access is granted for a specific granted authorities and authorities list
     *
     * @param authorities The authorities list for check
     * @return true if the granted authorities in authentication object contains any authority in specific authorities.
     * Otherwise, return false.
     */
    private static boolean hasAuthorityInName(final Collection<? extends GrantedAuthority> grantedAuthorities, final Collection<String> authorities) {
        if (CollectionUtils.isEmpty(grantedAuthorities) || CollectionUtils.isEmpty(authorities)) {
            return false;
        }

        return grantedAuthorities.stream().map(GrantedAuthority::getAuthority).anyMatch(authorities::contains);
    }

    /**
     * Determines if access is granted for a specific authentication and authorities list
     *
     * @param auth        The authentication for check
     * @param authorities The authorities list for check
     * @return true if the granted authorities in authentication object contains any authority in specific authorities.
     * Otherwise, return false.
     */
    private static boolean hasAuthorityInName(final Authentication auth, final Collection<String> authorities) {
        if (auth == null || !auth.isAuthenticated() || CollectionUtils.isEmpty(authorities)) {
            return false;
        }

        return hasAuthorityInName(auth.getAuthorities(), authorities);
    }

    /**
     * Determines if access is granted for a specific authentication and authorities list
     *
     * @param user        The user for check
     * @param authorities The authorities list for check
     * @return true if the granted authorities in user object contains any authority in specific authorities.
     * Otherwise, return false.
     */
    private static boolean hasAuthorityInName(final UserDetails user, final Collection<String> authorities) {
        if (user == null || CollectionUtils.isEmpty(authorities)) {
            return false;
        }

        return hasAuthorityInName(user.getAuthorities(), authorities);
    }

    /**
     * Determines if access is granted for a specific authentication and authority
     *
     * @param auth      The authentication for check
     * @param authority The authority for check
     * @return true if the granted authorities in authentication object contains authority.
     * Otherwise, return false.
     */
    public static boolean hasAuthority(final Authentication auth, final String authority) {
        return hasAuthorityInName(auth, Collections.singletonList(authority));
    }

    /**
     * Determines if access is granted for a specific authentication and authorities list
     *
     * @param auth        The authentication for check
     * @param authorities The authorities list for check
     * @return true if the granted authorities in authentication object contains any authority in specific authorities.
     * Otherwise, return false.
     */
    public static boolean hasAnyAuthority(final Authentication auth, final List<String> authorities) {
        return hasAuthorityInName(auth, authorities);
    }

    /**
     * Determines if access is granted for a specific user and authorities list
     *
     * @param user      The user for check
     * @param authority The authority for check
     * @return true if the granted authorities in user object contains authority.
     * Otherwise, return false.
     */
    public static boolean hasAuthority(final UserDetails user, final String authority) {
        return hasAuthorityInName(user, Collections.singletonList(authority));
    }

    /**
     * Determines if access is granted for a specific user and authorities list
     *
     * @param user        The user for check
     * @param authorities The authorities list for check
     * @return true if the granted authorities in user object contains any authority in specific authorities.
     * Otherwise, return false.
     */
    public static boolean hasAnyAuthority(final UserDetails user, final String... authorities) {
        return hasAuthorityInName(user, Arrays.asList(authorities));
    }

    /**
     * Determines if access is granted for a specific user and authorities list
     *
     * @param authorities The authorities list for check
     * @return true if the granted authorities in user object contains any authority in specific authorities.
     * Otherwise, return false.
     */
    public static boolean hasAnyAuthority(final Authentication auth, final String... authorities) {
        return hasAuthorityInName(auth, Arrays.asList(authorities));
    }
}
