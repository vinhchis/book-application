package vinhchis.factory;

public interface EntityFactory  {
    <E> E newEntity(Class<E> classType, String name);
}
