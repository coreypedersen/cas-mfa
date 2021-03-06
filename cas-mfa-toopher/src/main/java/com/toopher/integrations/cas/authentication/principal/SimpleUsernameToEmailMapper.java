// Generated by delombok at Mon Mar 10 08:01:35 CST 2014
package com.toopher.integrations.cas.authentication.principal;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.principal.Principal;

import javax.validation.constraints.NotNull;

public class SimpleUsernameToEmailMapper implements UsernameToEmailMapper {

    @NotNull
    private final String defaultEmailDomain;

    @NotNull
    private final String emailAttributeName;


    public SimpleUsernameToEmailMapper(final String defaultEmailDomain, final String emailAttributeName) {
        this.defaultEmailDomain = defaultEmailDomain;
        this.emailAttributeName = emailAttributeName;
    }

    public String getDefaultEmailDomain() {
        return this.defaultEmailDomain;
    }

    public String getEmailAttributeName() {
        return emailAttributeName;
    }

    @Override
    public String getEmailForUsername(final Principal principal) {
        final String email = (String) principal.getAttributes().get(this.emailAttributeName);
        if (StringUtils.isNotBlank(email)) {
            return email;
        }
        return principal.getId().concat(this.defaultEmailDomain);
    }
}
