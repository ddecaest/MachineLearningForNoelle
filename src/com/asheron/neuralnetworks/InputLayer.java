package com.asheron.neuralnetworks;

public class InputLayer extends Layer {

    private InputLayer(int nrNeurons) {
        super(nrNeurons);
    }

    public static InputLayer create(int nrNeurons) {
        return new InputLayer(nrNeurons);
    }

    @Override
    public void propogate(double[] neuronValuesPreviousLayer) {
        throw new RuntimeException("This is the input layer, dummy!");
    }
}
