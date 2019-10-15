package com.example.betmates.business;

import com.example.betmates.application.DBConnectionManager;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.Model;
import com.example.betmates.persistence.ModelPersistence;
import com.example.betmates.persistence.hsqldb.ModelPersistenceHSQLDB;
import com.example.betmates.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class AccessModelIT {

    private AccessModel accessModel;
    private File tempDB;
    private DBConnectionManager dbConnectionManager;
    private ArrayList<Model> models;

    @Before
    public void setUp() throws IOException {
        dbConnectionManager = new DBConnectionManager();
        this.tempDB = TestUtils.copyDB();
        String filePath = this.tempDB.getAbsolutePath().replace(".script", "");
        DBConnectionManager.setDBPathName(filePath);
        final ModelPersistence modelPersistence = new ModelPersistenceHSQLDB(dbConnectionManager);
        models = modelPersistence.getModelSequential();
        this.accessModel = new AccessModel();
        this.accessModel = new AccessModel(modelPersistence, models);
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
    public void testGetModelsWhenEmpty() throws IOException{
        dbConnectionManager = new DBConnectionManager();
        File tempDB = TestUtils.copyDB();
        String filePath = tempDB.getAbsolutePath().replace(".script", "");
        DBConnectionManager.setDBPathName(filePath);
        final ModelPersistence modelPersistence = new ModelPersistenceHSQLDB(dbConnectionManager);
        models = modelPersistence.getModelSequential();
        AccessModel accessModel = new AccessModel(modelPersistence, null);

        assertNotNull(accessModel.getModels());
    }


    @Test
    public void testGetModel() {
        final Model myModel = new Model();
        final Model returnedModel;
        System.out.println("\nStarting AccessModelTest: Get model from modelPersistence.");

        returnedModel = accessModel.getModel(0);
        assertNull("Returned model should be null", returnedModel);

        System.out.println("\nFinished AccessUsersTest: Get model from modelPersistence.");
    }


    @Test
    public void testDeleteModel() {
        final Model model = new Model();

        System.out.println("\nStarting AccessModelTest: Delete model from modelPersistence.");

        assertTrue("Should return true when delete existing model", accessModel.deleteModel(model));
        assertNull("Should return null. since already delete that model", accessModel.getModel(model.getId()));


        System.out.println("\nFinished AccessModelTest: Delete model from modelPersistence.");
    }

    @Test
    public void testinsertModel() {
        Model model = new Model(1, 1, 1, 1, "name", "time", "status", new Bet(10, "des", "name"));
        assertNull(accessModel.insertModel(model));
    }

    @Test
    public void testUpdateModel() {
        Model model = new Model(1, 1, 1, 1, "name", "time", "status", new Bet(10, "des", "name"));
        assertNull(accessModel.updateModel(model));
    }

    @Test
    public void testGetNewId() {
        assertNotNull(accessModel.getNewid());
    }

    @After
    public void tearDown() {
        //reset DB
        this.tempDB.delete();
    }


}
