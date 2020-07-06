package com.tianyang.jetpackdemo.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.tianyang.jetpackdemo.MyApplication;
import com.tianyang.jetpackdemo.database.entity.Article;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 界面描述：
 * <p>
 * Created by tianyang on 2020/7/1.
 * exportSchema默认为true，会导出数据库文件，导出位置在gradle中配置
 */
@Database(entities = {Article.class}, version = 1, exportSchema = true)
public abstract class AppDataBase extends RoomDatabase {

    public abstract ArticleDao articleDao();

    private static volatile AppDataBase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getDatabase() {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
//                    创建一个内存数据库, 但是这种数据库的数据只存在于内存中，也就是进程被杀之后，数据随之丢失
//                    Room.inMemoryDatabaseBuilder()
                    INSTANCE = Room.databaseBuilder(MyApplication.instance, AppDataBase.class, "app_database")
//                            //允许在主线程进行查询
//                            .allowMainThreadQueries()
//                            //数据库创建和打开后的回调
                            .addCallback(sRoomDatabaseCallback)
//                            //设置查询的线程池
//                            //.setQueryExecutor()
//                            .openHelperFactory()
//                            //room的日志模式
//                            .setJournalMode()
//                            //数据库升级异常之后的回滚
//                            .fallbackToDestructiveMigration()
//                            //数据库升级异常后根据指定版本进行回滚
//                            .fallbackToDestructiveMigrationFrom()
//                            .addMigrations(AppDataBase.MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table article rename to student");
            database.execSQL("alter table article add column age INTEGER NOT NULL default 0");
            database.execSQL("alter table article add column describe TEXT");
        }
    };

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                ArticleDao dao = INSTANCE.articleDao();
                dao.deleteAll();
            });
        }
    };
}
