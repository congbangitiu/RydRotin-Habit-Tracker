package com.project.rydrotin.local;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.project.rydrotin.local.entities.HabitWithHistory;
import com.project.rydrotin.local.entities.Habits;
import com.project.rydrotin.local.entities.History;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HabitsDAO_Impl implements HabitsDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Habits> __insertionAdapterOfHabits;

  private final EntityDeletionOrUpdateAdapter<Habits> __updateAdapterOfHabits;

  private final SharedSQLiteStatement __preparedStmtOfDeleteHabit;

  public HabitsDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHabits = new EntityInsertionAdapter<Habits>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `habits_table` (`username`,`name`,`countPerDay`,`habitId`) VALUES (?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Habits value) {
        if (value.getUsername() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUsername());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getCountPerDay());
        stmt.bindLong(4, value.getHabitId());
      }
    };
    this.__updateAdapterOfHabits = new EntityDeletionOrUpdateAdapter<Habits>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `habits_table` SET `username` = ?,`name` = ?,`countPerDay` = ?,`habitId` = ? WHERE `habitId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Habits value) {
        if (value.getUsername() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getUsername());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getCountPerDay());
        stmt.bindLong(4, value.getHabitId());
        stmt.bindLong(5, value.getHabitId());
      }
    };
    this.__preparedStmtOfDeleteHabit = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from habits_table WHERE habitId = ? and username = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertHabit(final Habits habits, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHabits.insert(habits);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateHabit(final Habits habits, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHabits.handle(habits);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteHabit(final long habitId, final String username,
      final Continuation<? super Integer> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteHabit.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, habitId);
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
          __preparedStmtOfDeleteHabit.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public List<Habits> getHabits(final String username) {
    final String _sql = "select * from habits_table WHERE username = ?";
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
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfCountPerDay = CursorUtil.getColumnIndexOrThrow(_cursor, "countPerDay");
      final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
      final List<Habits> _result = new ArrayList<Habits>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Habits _item;
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final int _tmpCountPerDay;
        _tmpCountPerDay = _cursor.getInt(_cursorIndexOfCountPerDay);
        _item = new Habits(_tmpUsername,_tmpName,_tmpCountPerDay);
        final long _tmpHabitId;
        _tmpHabitId = _cursor.getLong(_cursorIndexOfHabitId);
        _item.setHabitId(_tmpHabitId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public HabitWithHistory getHabitWithHistoryById(final long habitId, final String username) {
    final String _sql = "SELECT * FROM habits_table WHERE habitId = ? and username = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, habitId);
    _argIndex = 2;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
        final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
        final int _cursorIndexOfCountPerDay = CursorUtil.getColumnIndexOrThrow(_cursor, "countPerDay");
        final int _cursorIndexOfHabitId = CursorUtil.getColumnIndexOrThrow(_cursor, "habitId");
        final LongSparseArray<ArrayList<History>> _collectionHistory = new LongSparseArray<ArrayList<History>>();
        while (_cursor.moveToNext()) {
          final long _tmpKey = _cursor.getLong(_cursorIndexOfHabitId);
          ArrayList<History> _tmpHistoryCollection = _collectionHistory.get(_tmpKey);
          if (_tmpHistoryCollection == null) {
            _tmpHistoryCollection = new ArrayList<History>();
            _collectionHistory.put(_tmpKey, _tmpHistoryCollection);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshiphistoryTableAscomProjectRydrotinLocalEntitiesHistory(_collectionHistory);
        final HabitWithHistory _result;
        if(_cursor.moveToFirst()) {
          final Habits _tmpHabits;
          if (! (_cursor.isNull(_cursorIndexOfUsername) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfCountPerDay) && _cursor.isNull(_cursorIndexOfHabitId))) {
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpCountPerDay;
            _tmpCountPerDay = _cursor.getInt(_cursorIndexOfCountPerDay);
            _tmpHabits = new Habits(_tmpUsername,_tmpName,_tmpCountPerDay);
            final long _tmpHabitId;
            _tmpHabitId = _cursor.getLong(_cursorIndexOfHabitId);
            _tmpHabits.setHabitId(_tmpHabitId);
          }  else  {
            _tmpHabits = null;
          }
          ArrayList<History> _tmpHistoryCollection_1 = null;
          final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfHabitId);
          _tmpHistoryCollection_1 = _collectionHistory.get(_tmpKey_1);
          if (_tmpHistoryCollection_1 == null) {
            _tmpHistoryCollection_1 = new ArrayList<History>();
          }
          _result = new HabitWithHistory(_tmpHabits,_tmpHistoryCollection_1);
        } else {
          _result = null;
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshiphistoryTableAscomProjectRydrotinLocalEntitiesHistory(
      final LongSparseArray<ArrayList<History>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<History>> _tmpInnerMap = new LongSparseArray<ArrayList<History>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshiphistoryTableAscomProjectRydrotinLocalEntitiesHistory(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<History>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshiphistoryTableAscomProjectRydrotinLocalEntitiesHistory(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `username`,`habitId`,`habitName`,`countDone`,`countPerDay`,`date`,`message`,`historyId` FROM `history_table` WHERE `habitId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "habitId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfUsername = 0;
      final int _cursorIndexOfHabitId = 1;
      final int _cursorIndexOfHabitName = 2;
      final int _cursorIndexOfCountDone = 3;
      final int _cursorIndexOfCountPerDay = 4;
      final int _cursorIndexOfDate = 5;
      final int _cursorIndexOfMessage = 6;
      final int _cursorIndexOfHistoryId = 7;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        ArrayList<History> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final History _item_1;
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
          _item_1 = new History(_tmpUsername,_tmpHabitId,_tmpHabitName,_tmpCountDone,_tmpCountPerDay,_tmpDate,_tmpMessage);
          final long _tmpHistoryId;
          _tmpHistoryId = _cursor.getLong(_cursorIndexOfHistoryId);
          _item_1.setHistoryId(_tmpHistoryId);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
