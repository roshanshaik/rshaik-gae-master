

package com.rshaik.rshaikhello.daos;

import com.rshaik.rshaikhello.objects.Audit;
import com.rshaik.rshaikhello.objects.Result;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * Google Datastore DAO for Audit objects
 * 
 * @author rshaik
 *
 */
public class DatastoreDao implements AuditDao {


  private DatastoreService datastore;
  private static final String AUDIT_KIND = "Audit2";

  public DatastoreDao() {
    datastore = DatastoreServiceFactory.getDatastoreService(); // Authorized Datastore service
  }

  public Audit entityToAudit(Entity entity) {
    return new Audit.Builder()                                     // Convert to Audit form
        .json((String)entity.getProperty(Audit.JSON))
        .createdDate((String)entity.getProperty(Audit.CREATED_DATE))
        .id(entity.getKey().getId())
        .ipAddress((String)entity.getProperty(Audit.IP_ADDRESS))
        .build();
  }

  @Override
  public Long createAudit(Audit audit) {
    Entity incAuditEntity = new Entity(AUDIT_KIND);  // Key will be assigned once written
    incAuditEntity.setProperty(Audit.JSON, audit.getJson());
    incAuditEntity.setProperty(Audit.CREATED_DATE, audit.getCreatedDate());
    incAuditEntity.setProperty(Audit.IP_ADDRESS, audit.getIpAddress());
   

    Key auditKey = datastore.put(incAuditEntity); // Save the Entity
    return auditKey.getId();                     // The ID of the Key
  }
 
 
 
}

