package br.com.reducicla.dto.response;

import br.com.reducicla.enumerated.Role;

/**
 * @author User on 19/05/2021
 */
public class CredencialResponseDTO {
    private Long id;
    private String email;
    private Role role;
    private String token;


    public static final class Builder {
        private Long id;
        private String email;
        private Role role;
        private String token;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public CredencialResponseDTO build() {
            CredencialResponseDTO credencialResponseDTO = new CredencialResponseDTO();
            credencialResponseDTO.id = this.id;
            credencialResponseDTO.email = this.email;
            credencialResponseDTO.role = this.role;
            credencialResponseDTO.token = this.token;
            return credencialResponseDTO;
        }
    }
}
