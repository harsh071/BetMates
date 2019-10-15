package com.example.betmates.business;

import com.example.betmates.application.Services;
import com.example.betmates.objects.content.Model;
import com.example.betmates.persistence.ModelPersistence;

import java.util.ArrayList;

public class AccessModel {
    private ModelPersistence modelPersistence;
    private ArrayList<Model> models;

    public AccessModel() {
        modelPersistence = Services.getModelPersistence();
        models= modelPersistence.getModelSequential();
    }

    public AccessModel(ModelPersistence modelPersistence, ArrayList<Model> models) {
        this.modelPersistence = modelPersistence;
        this.models = models;
    }

    public ArrayList<Model> getModels() {
        if (models==null || models.size()==0) {
            models = modelPersistence.getModelSequential();
        }
        return models;
    }

    public Model getModel(int id) {
        Model model = null;
        boolean modelFound = false;
        for (int i = 0; i < models.size(); i++) {
            if(!modelFound) {
                model = models.get(i);
                modelFound = model.getId() == id;
            }
        }
        if (!modelFound) {
            model = null;
        }
        return model;
    }

    public Model insertModel(Model currentModel) {
        return modelPersistence.insertModel(currentModel);
    }

    public Model updateModel(Model currentModel) {
        return modelPersistence.updateModel(currentModel);
    }

    public boolean deleteModel(Model currentModel) {
        return (modelPersistence.deleteModel(currentModel));
    }

    public int getNewid(){return modelPersistence.getId();}

}
