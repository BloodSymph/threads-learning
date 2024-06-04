package org.example;

public final class ExchangedObject {

    private final long id;

    public ExchangedObject(final long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExchangedObject{" +
                "id=" + id +
                '}';
    }

}
