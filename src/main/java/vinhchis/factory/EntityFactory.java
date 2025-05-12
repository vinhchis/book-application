package vinhchis.factory;

import vinhchis.builder.Builder;

public interface EntityFactory  {
    <E> E newEntity(Class<E> classType, String name);
    <E, B extends Builder<E>> B newEntityBuilder(Class<B> builderType);
}
