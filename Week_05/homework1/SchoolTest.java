package springtest;

import java0.spring.ISchool;
import java0.spring.TeachConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TeachConfig.class)
public class SchoolTest {

    @Autowired
    private ISchool school;

    @Test
    public void play() {

        school.build();

    }

}
