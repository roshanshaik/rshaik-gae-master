

package com.rshaik.rshaikhello.daos;

import com.rshaik.rshaikhello.objects.Audit;
import com.rshaik.rshaikhello.objects.Result;

import java.sql.SQLException;


public interface AuditDao {
  Long createAudit(Audit audit) throws SQLException;
}

