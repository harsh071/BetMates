package com.example.betmates.persistence;

import com.example.betmates.objects.content.Model;

import java.util.ArrayList;

public interface ModelPersistence {
    ArrayList<Model> getModelSequential();
    Model insertModel(Model newBet);
    Model updateModel(Model newBet);
    boolean deleteModel(Model currentBet);
    int getId();
}
