package pl.gielerak.domain.repository.model;

import pl.gielerak.domain.owner.model.Owner;

import java.util.Objects;

public class FullName {
    final private Owner owner;
    final private Name name;

    public FullName(Owner owner, Name name) {
        this.owner = owner;
        this.name = name;
    }

    public static FullName fromString(String fullName) {
        String[] string = fullName.split("/");

        return new FullName(new Owner(string[0]), new Name(string[1]));
    }

    public String toString() {
        return getFullName();
    }

    public String getFullName() {
        return String.format("%s/%s", owner.getName(), name.getName());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FullName)) {
            return false;
        }

        return Objects.equals(((FullName) object).owner.getName(), this.owner.getName())
                && Objects.equals(((FullName) object).name.getName(), this.name.getName());
    }
}
