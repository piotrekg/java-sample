package pl.gielerak.domain.owner.model;

public class Owner {
    final private String name;

    public Owner(String name) {
        if (false == name.matches("[\\w-]+")) {
            throw new IllegalArgumentException(name);
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
