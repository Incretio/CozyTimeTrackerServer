package com.incretio.cozy_time_tracker_server.repository.memory;

import com.incretio.cozy_time_tracker_server.model.local.tag.TagPOJO;
import com.incretio.cozy_time_tracker_server.model.local.task.TaskPOJO;
import com.incretio.cozy_time_tracker_server.model.local.timeinterval.TimeIntervalPOJO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SharedMemoryData {

    private List<TagPOJO> tagsList = new ArrayList<>();

    public List<TagPOJO> getTagsList() {
        return tagsList;
    }

    {
        tagsList.add(new TagPOJO(0, "Все"));
        tagsList.add(new TagPOJO(1, "Спринт"));
        tagsList.add(new TagPOJO(2, "Lunda.ru"));
        tagsList.add(new TagPOJO(3, "Lunda.se"));
        tagsList.add(new TagPOJO(4, "Purchase"));
    }

    private List<TaskPOJO> tasksList = new ArrayList<>();

    public List<TaskPOJO> getTasksList() {
        return tasksList;
    }

    private TimeIntervalPOJO generateTimeInterval(int year, int month, int day, int hour, int minute) {
        return new TimeIntervalPOJO(
            new DateTime(day, month, year, hour, minute).toDate(),
            new DateTime(day, month, year, hour, minute).plusMinutes(30).toDate(),
            "message"
        );
    }

    // @formatter:off
    {
        TaskPOJO task = TaskPOJO.builder()
            .id(1)
            .tagsList(new ArrayList<>(Arrays.asList(1,2)))
            .number("LNDR-0001")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(10*60*60*1000)
            .timeIntervalPOJOList(new ArrayList<>(Arrays.asList(
                generateTimeInterval(27, 1, 2020, 10, 0),
                generateTimeInterval(28, 1, 2020, 12, 0),
                generateTimeInterval(29, 1, 2020, 14, 30)
            )))
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(2)
            .tagsList(new ArrayList<>(Arrays.asList(1)))
            .number("LNDR-0002")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(3)
            .tagsList(new ArrayList<>(Arrays.asList(2,3)))
            .number("LNDR-0003")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(4)
            .tagsList(new ArrayList<>(Arrays.asList(3,4)))
            .number("LNDR-0004")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .timeIntervalPOJOList(new ArrayList<>(Arrays.asList(
                generateTimeInterval(27, 1, 2020, 10, 0),
                generateTimeInterval(28, 1, 2020, 12, 0),
                generateTimeInterval(29, 1, 2020, 14, 30)
            )))
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(5)
            .tagsList(new ArrayList<>(Arrays.asList(1,3,4)))
            .number("LNDR-0005")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);

        task = TaskPOJO.builder()
            .id(6)
            .tagsList(new ArrayList<>(Arrays.asList()))
            .number("LNDR-0006")
            .name("Остаток на складах шоурум выводить отдельно (ТЗ)")
            .timeLimit(60*1000)
            .build();
        tasksList.add(task);
    }
    // @formatter:on


    public List<TimeIntervalPOJO> getTimeIntervalList() {
        List<TimeIntervalPOJO> result = new ArrayList<>();
        for (TaskPOJO task : getTasksList()) {
            result.addAll(task.getTimeIntervalPOJOList());
        }
        return result;
    }

}
