package ru.filemanager.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TimeService {
    public Date getDate() {
        return Calendar.getInstance().getTime();
    }
}
