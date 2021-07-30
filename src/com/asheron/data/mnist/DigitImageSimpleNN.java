package com.asheron.data.mnist;

import com.asheron.neuralnetworks.InputLayer;
import com.asheron.neuralnetworks.Layer;
import com.asheron.neuralnetworks.LayerWithWeights;

import java.util.Collections;
import java.util.List;

public class DigitImageSimpleNN {

    private final Layer[] layers;

    public DigitImageSimpleNN(Integer... neuronsPerLayer) {
        layers = new Layer[neuronsPerLayer.length];

        layers[0] = InputLayer.create(neuronsPerLayer[0]);
        for(int i=1; i<layers.length; i++) {
            layers[i] = LayerWithWeights.create(neuronsPerLayer[i], neuronsPerLayer[i-1]);
        }
    }

    public void trainWithMiniBatches(List<DigitImageWithLabel> trainingData, int miniBatchSize) {

        Collections.shuffle(trainingData);

        final int nrBatches = (int) Math.floor(trainingData.size()/miniBatchSize);
        for(int i=0; i<nrBatches; i++) {
            trainWithMiniBatch(trainingData.subList(i * miniBatchSize, (i + 1) * miniBatchSize));
        }
    }

    public void trainWithMiniBatch(List<DigitImageWithLabel> miniBatch) {

    }

    public void offerInput(double[] input) {
        layers[0].setValuesOfNeurons(input);

        for(int i = 1; i< layers.length; i++) {
            layers[i].propogate(layers[i-1].neurons);
        }
    }

    public int getNeuronIndexOfHighestOutput() {
        final double[] outputLayer = layers[layers.length-1].neurons;

        int indexToReturn = 0;
        double highestValue = outputLayer[0];
        for(int i=1; i<outputLayer.length; i++) {
            if(outputLayer[i] > highestValue) {
                highestValue = outputLayer[i];
                indexToReturn = i;
            }
        }

        return indexToReturn;
    }

    public double[] checkOutputLayer() {
        return layers[layers.length-1].neurons;
    }
}
