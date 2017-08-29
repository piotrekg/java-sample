package pl.gielerak.domain.repository.model;

public class Name {
    final private String name;

    public Name(String name) {
        if (false == name.matches("[\\w-]+")) {
            throw new IllegalArgumentException(name);
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
