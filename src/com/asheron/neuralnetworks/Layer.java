package com.asheron.neuralnetworks;

public abstract class Layer {

    public final double neurons[];

    Layer(int nrOfNeurons) {
        this.neurons = new double[nrOfNeurons];
    }

    public void setValuesOfNeurons(double[] neuronValues) {
        System.arraycopy(neuronValues, 0, this.neurons, 0, neuronValues.length);
    }

    public abstract void propogate(double[] neuronValuesPreviousLayer);
}
