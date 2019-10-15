package com.example.betmates.business;

import com.example.betmates.objects.content.Model;
import com.example.betmates.persistence.ModelPersistence;
import com.example.betmates.persistence.stubs.ModelPersistenceStub;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


public class AccessModelTest {

    private AccessModel accessModel;
    private ModelPersistence modelPersistence = new ModelPersistenceStub();
    private ArrayList<Model> models = modelPersistence.getModelSequential();

    @Before
    public void setUp() {
        accessModel = new AccessModel(modelPersistence, models);
    }

    @Test
    public void testGetModels() {
        final ArrayList<Model> returnModels;
        System.out.println("\nStarting AccessModelTest: Get list of models from modelPersistence.");

        returnModels = accessModel.getModels();
        assertNotNull("List of models should not be null", returnModels);
        assertSame("List of models should be the same ArrayList<models> object as the object passed through constructor.", models, returnModels);

        System.out.println("\nFinished AccessUsersTest: Get list of models from modelPersistence.");
    }

    @Test
    public void testGetModel() {
        final Model myModel = new Model();
        final Model returnedModel;
        accessModel.insertModel(myModel);
        System.out.println("\nStarting AccessModelTest: Get model from modelPersistence.");

        returnedModel = accessModel.getModel(myModel.getId());
        assertNotNull("Returned model should not be null", returnedModel);
        assertSame("The returnedModel should be the same as pass in", myModel, returnedModel);

        System.out.println("\nFinished AccessUsersTest: Get model from modelPersistence.");
    }

    @Test
    public void testInsertModel() {
        final Model myModel = new Model();
        final Model returnedModel;
        System.out.println("\nStarting AccessModelTest: Insert model to modelPersistence.");

        returnedModel = accessModel.insertModel(myModel);
        assertNotNull("Returned model should not be null", returnedModel);
        assertSame("The returnedModel should be the same as pass in", myModel, returnedModel);

        System.out.println("\nFinished AccessUsersTest: Insert model to modelPersistence.");
    }

    @Test
    public void testUpdateBet() {
        final Model myModel = new Model();
        final Model returnedModel;
        System.out.println("\nStarting AccessModelTest: Update model to modelPersistence.");

        returnedModel = accessModel.updateModel(myModel);
        assertNotNull("Returned model should not be null", returnedModel);
        assertSame("The returnedModel should be the same as pass in", myModel, returnedModel);

        System.out.println("\nFinished AccessUsersTest: Update model to modelPersistence.");
    }

    @Test
    public void testDeleteModel() {
        final Model model = new Model();
        modelPersistence.insertModel(model);
        System.out.println("\nStarting AccessModelTest: Delete model from modelPersistence.");

        assertTrue("Should return true when delete existing model", accessModel.deleteModel(model));
        assertNull("Should return null. since already delete that model", accessModel.getModel(model.getId()));


        System.out.println("\nFinished AccessModelTest: Delete model from modelPersistence.");
    }
}
