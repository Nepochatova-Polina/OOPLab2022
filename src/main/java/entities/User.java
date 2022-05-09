package entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
        private int id;
        private final UserRole role;
        private final String name;
        private final String password;

        public User(String name, String password, UserRole type) {
            this.name = name;
            this.password = password;
            this.role = type;
        }

        public User(int id, String name, String password, UserRole type) {
            this.id = id;
            this.name = name;
            this.password = password;
            this.role = type;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;
            User registeredUser = ((User) obj);
            return id == registeredUser.id;
        }
    }
