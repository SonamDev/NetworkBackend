package net.grian.atlas.request;

public abstract class RequestHandle<T> {

    private Class<T> mapping;

    public RequestHandle(Class<T> mapping) {
        this.mapping = mapping;
    }

    public abstract void handle(T t);

}
