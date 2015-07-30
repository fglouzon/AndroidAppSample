package com.octo.android.robospice.persistence.ormlite;

import android.app.Application;

import java.util.List;

public class InDatabaseObjectPersisterFactoryWithContentProvider extends InDatabaseObjectPersisterFactory {

    public InDatabaseObjectPersisterFactoryWithContentProvider(Application application, RoboSpiceDatabaseHelper databaseHelper, List<Class<?>> listHandledClasses) {
        super(application, databaseHelper, ContractHelper.getContractClasses(listHandledClasses));
    }

}
