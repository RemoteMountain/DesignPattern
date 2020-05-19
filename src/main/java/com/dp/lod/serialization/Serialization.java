package com.dp.lod.serialization;


public class Serialization implements Serializable,Deserializable{

    @Override
    public String serialize(Object object) {
        String serializedResult = ...;
        //...
        return serializedResult;
    }

    @Override
    public Object deserialize(String str) {
        Object deserializedResult = ...;
        //...
        return deserializedResult;
    }
}
