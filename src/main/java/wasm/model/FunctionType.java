package wasm.model;

import wasm.model.tag.FunctionTypeTag;
import wasm.model.type.ValueType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FunctionType {

    public FunctionTypeTag tag;

    public ValueType[] parameters;

    public ValueType[] results;

    public FunctionType(FunctionTypeTag tag, ValueType[] parameters, ValueType[] results) {
        this.tag = tag;
        this.parameters = parameters;
        this.results = results;
    }

    @Override
    public String toString() {
        return "FunctionType{" +
                "tag=" + tag +
                ", parameters=" + Arrays.toString(parameters) +
                ", results=" + Arrays.toString(results) +
                '}';
    }

    public String dump(int index) {
        return "type[" + index + "]: (" +
                Arrays.stream(parameters).map(ValueType::name)
                        .collect(Collectors.joining(",")) +
                ")->(" +
                Arrays.stream(results).map(ValueType::name)
                        .collect(Collectors.joining(",")) +
                ")";
    }

    public boolean same(FunctionType other) {
        if (tag.value() != other.tag.value()) { return false; }
        if (parameters.length != other.parameters.length) { return false; }
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].value() != other.parameters[i].value()) {
                return false;
            }
        }
        if (results.length != other.results.length) { return false; }
        for (int i = 0; i < results.length; i++) {
            if (results[i].value() != other.results[i].value()) {
                return false;
            }
        }
        return true;
    }

}
