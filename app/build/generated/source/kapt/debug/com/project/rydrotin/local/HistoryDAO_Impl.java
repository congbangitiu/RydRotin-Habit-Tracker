package com.project.rydrotin.local;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.project.rydrotin.local.entities.History;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HistoryDAO_Impl implements HistoryDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<History> __insertionAdapterOfHistory;

  private final EntityDeletionOrUpdateAdapter<History> __updateAdapterOfHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteHistory;

  public HistoryDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistory = new EntityInsertionAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `history_table` (`username`,`habitId`,`habitName`,`countDone`,`countPerDay`,`date`,`message`,`historyId`) VALUES (?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        if (value.getUsername() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUsername());
        }
        stmt.bindLong(2, value.getHabitId());
        if (value.getHabitName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getHabitName());
        }
        stmt.bindLong(4, value.getCountDone());
        stmt.bindLong(5, value.getCountPerDay());
        if (value.getDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMessage());
        }
        stmt.bindLong(8, value.getHistoryId());
      }
    };
    this.__updateAdapterOfHistory = new EntityDeletionOrUpdateAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `history_table` SET `username` = ?,`habitId` = ?,`habitName` = ?,`countDone` = ?,`countPerDay` = ?,`date` = ?,`message` = ?,`historyId` = ? WHERE `historyId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        if (value.getUsername() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUsername());
        }
        stmt.bindLong(2, value.getHabitId());
        if (value.getHabitName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getHabitName());
        }
        stmt.bindLong(4, value.getCountDone());
        stmt.bindLong(5, value.getCountPerDay());
        if (value.getDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMessage());
        }
        stmt.bindLong(8, value.getHistoryId());
        stmt.bindLong(9, value.getHistoryId());
      }
    };
    this.__preparedStmtOfDeleteHistory = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from history_table WHERE historyId = ? and username = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertHistory(final History history,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHistory.insert(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateHistory(final History history,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHistory.handle(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteHistory(final long historyId, final String username,
      final Continuation<? super Integer> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteHistory.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, historyId);
        _argIndex = 2;
        if (username == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, username);
        }
        __db.beginTransaction();
        try {
          final java.lang.Integer _result = _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteHistory.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public List<History> getHistory(final String username) {
    final String _sql = "select * from history_table WHERE username = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
      final int _cursorIndexOfHabitName = CursorUtil.getColumnIndexOrThrow(_cursor, "habitName");
      final int _cursorIndexOfCountDone = CursorUtil.getColumnIndexOrThrow(_cursor, "countDone");
      final int _cursorIndexOfCountPerDay = CursorUtil.getColumnIndexOrThrow(_cursor, "countPerDay");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfHistoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "historyId");
      final List<History> _result = new ArrayList<History>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final History _item;
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final long _tmpHabitId;
        _tmpHabitId = _cursor.getLong(_cursorIndexOfHabitId);
        final String _tmpHabitName;
        if (_cursor.isNull(_cursorIndexOfHabitName)) {
          _tmpHabitName = null;
        } else {
          _tmpHabitName = _cursor.getString(_cursorIndexOfHabitName);
        }
        final int _tmpCountDone;
        _tmpCountDone = _cursor.getInt(_cursorIndexOfCountDone);
        final int _tmpCountPerDay;
        _tmpCountPerDay = _cursor.getInt(_cursorIndexOfCountPerDay);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _item = new History(_tmpUsername,_tmpHabitId,_tmpHabitName,_tmpCountDone,_tmpCountPerDay,_tmpDate,_tmpMessage);
        final long _tmpHistoryId;
        _tmpHistoryId = _cursor.getLong(_cursorIndexOfHistoryId);
        _item.setHistoryId(_tmpHistoryId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public History getTodayHistory(final String date, final long habitId, final String username) {
    final String _sql = "select * from history_table WHERE date = ? and habitId = ? and username = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, habitId);
    _argIndex = 3;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
      final int _cursorIndexOfHabitName = CursorUtil.getColumnIndexOrThrow(_cursor, "habitName");
      final int _cursorIndexOfCountDone = CursorUtil.getColumnIndexOrThrow(_cursor, "countDone");
      final int _cursorIndexOfCountPerDay = CursorUtil.getColumnIndexOrThrow(_cursor, "countPerDay");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfHistoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "historyId");
      final History _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final long _tmpHabitId;
        _tmpHabitId = _cursor.getLong(_cursorIndexOfHabitId);
        final String _tmpHabitName;
        if (_cursor.isNull(_cursorIndexOfHabitName)) {
          _tmpHabitName = null;
        } else {
          _tmpHabitName = _cursor.getString(_cursorIndexOfHabitName);
        }
        final int _tmpCountDone;
        _tmpCountDone = _cursor.getInt(_cursorIndexOfCountDone);
        final int _tmpCountPerDay;
        _tmpCountPerDay = _cursor.getInt(_cursorIndexOfCountPerDay);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _result = new History(_tmpUsername,_tmpHabitId,_tmpHabitName,_tmpCountDone,_tmpCountPerDay,_tmpDate,_tmpMessage);
        final long _tmpHistoryId;
        _tmpHistoryId = _cursor.getLong(_cursorIndexOfHistoryId);
        _result.setHistoryId(_tmpHistoryId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<History> getAllTodayHistory(final String date, final String username) {
    final String _sql = "select * from history_table WHERE date = ? and username = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
      final int _cursorIndexOfHabitName = CursorUtil.getColumnIndexOrThrow(_cursor, "habitName");
      final int _cursorIndexOfCountDone = CursorUtil.getColumnIndexOrThrow(_cursor, "countDone");
      final int _cursorIndexOfCountPerDay = CursorUtil.getColumnIndexOrThrow(_cursor, "countPerDay");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfHistoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "historyId");
      final List<History> _result = new ArrayList<History>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final History _item;
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final long _tmpHabitId;
        _tmpHabitId = _cursor.getLong(_cursorIndexOfHabitId);
        final String _tmpHabitName;
        if (_cursor.isNull(_cursorIndexOfHabitName)) {
          _tmpHabitName = null;
        } else {
          _tmpHabitName = _cursor.getString(_cursorIndexOfHabitName);
        }
        final int _tmpCountDone;
        _tmpCountDone = _cursor.getInt(_cursorIndexOfCountDone);
        final int _tmpCountPerDay;
        _tmpCountPerDay = _cursor.getInt(_cursorIndexOfCountPerDay);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _item = new History(_tmpUsername,_tmpHabitId,_tmpHabitName,_tmpCountDone,_tmpCountPerDay,_tmpDate,_tmpMessage);
        final long _tmpHistoryId;
        _tmpHistoryId = _cursor.getLong(_cursorIndexOfHistoryId);
        _item.setHistoryId(_tmpHistoryId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
