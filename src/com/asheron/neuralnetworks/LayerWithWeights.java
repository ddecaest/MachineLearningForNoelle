package com.asheron.neuralnetworks;

import com.asheron.util.RandomUtil;
import com.asheron.util.MathUtils;

public class LayerWithWeights extends Layer {

    // List of weights entering each neuron
    final double[][] enteringWeights;
    // List of bias, one per neuron
    final int[] biasPerNeuron;

    private LayerWithWeights(int nrNeuronsInThis, int nrNeuronsInPrevious) {
        super(nrNeuronsInThis);

        this.enteringWeights = new double[nrNeuronsInThis][nrNeuronsInPrevious];
        this.biasPerNeuron = new int[nrNeuronsInThis];

        randomizeBiases();
        randomizeWeights();
    }

    private void randomizeBiases() {
        for(int i=0; i<biasPerNeuron.length; i++) {
            biasPerNeuron[i] = RandomUtil.random.nextInt(5);
        }
    }

    private void randomizeWeights() {
        for (double[] weightsForNeuron : this.enteringWeights) {
            for(int i=0; i<weightsForNeuron.length; i++) {
                weightsForNeuron[i] = (RandomUtil.random.nextDouble() * 2) - 1;
            }
        }
    }

    public static LayerWithWeights create(int nrNeuronsInThis, int nrNeuronsInPrevious) {
        return new LayerWithWeights(nrNeuronsInThis, nrNeuronsInPrevious);
    }

    @Override
    public void propogate(double[] neuronValuesPreviousLayer) {
        // This can be done with matrix multiplication
        // Calculate the new value for each neuron
        for(int i = 0; i< neurons.length; i++) {
            neurons[i] = calculateNewNeuronValue(i, neuronValuesPreviousLayer);
        }
    }

    private double calculateNewNeuronValue(int neuronIndex, double[] neuronValuesPreviousLayer) {
        // Sum of each previous neuron value + weight connecting it to this one...
        double newValue = MathUtils.dotProduct(enteringWeights[neuronIndex], neuronValuesPreviousLayer);
        // ...minus the bias...
        newValue = newValue - biasPerNeuron[neuronIndex];
        // ...and finally a squishification function
        return MathUtils.sigmoid(newValue);
    }
}