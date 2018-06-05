package com.example.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greendao.bean.Student;
import com.example.greendao.db.StudentDaoOpe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.btn_query_all)
    Button btnQueryAll;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private List<Student> studentList = new ArrayList<>();

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 10; i++) {
            Student student = new Student((long) i, "z" + i, 25, "n" + i,"");
            studentList.add(student);
        }
    }

    int page;

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.btn_query_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button://增加
                StudentDaoOpe.insertData(this, studentList);
                break;
            case R.id.button2://删除
                Student student = new Student((long) 5, "zhao" + 5, 25, "111","");
                /**
                 * 根据特定的对象删除
                 */
//                StudentDaoOpe.deleteData(this, student);
                /**
                 * 根据主键删除
                 */
                StudentDaoOpe.deleteByKeyData(this, 7);
//                StudentDaoOpe.deleteAllData(this);
                break;
            case R.id.button3://修改
                student = new Student((long) 2, "caojin", 1314, "888888","");
                //按照上面的id更新对应的数据库中的数据
                StudentDaoOpe.updateData(this, student);
                break;
            case R.id.button4://查询
                List<Student> students = StudentDaoOpe.queryAll(this);
                tvContent.setText(students.toString());
                for (int i = 0; i < students.size(); i++) {
                    Log.i("Log", students.get(i).getName());
                }
                break;
            case R.id.button5://删除全部
                StudentDaoOpe.deleteAllData(this);
                break;
            case R.id.btn_query_all://查询全部
                List<Student> students2 = StudentDaoOpe.queryPaging(page, 20, this);
                if (students2.size() == 0) {
                    Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
                for (Student st : students2) {
                    Log.e("TAG", "onViewClicked: ==" + st);
                    Log.e("TAG", "onViewClicked: == num = " + st.getNum());
                }
                page++;
                tvContent.setText(students2.toString());
                break;
        }
    }
}
