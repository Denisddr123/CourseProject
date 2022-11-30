package com.students.webappwithsecurity.controller;

import com.students.webappwithsecurity.entity.Song;
import com.students.webappwithsecurity.repository.SongRepository;
import com.students.webappwithsecurity.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class SongController {
    @Autowired
    private SongRepository songRepository;
    private LogMessageService makeLogMessageService;

    public SongController(LogMessageService makeLogMessageService) {
        this.makeLogMessageService = makeLogMessageService;
    }

    @GetMapping("/listSong")
    public ModelAndView getAllStudents() {
        ModelAndView modelAndView = new ModelAndView("list-song");
        modelAndView.addObject("song", songRepository.findAll());
        makeLogMessageService.makeMessage("/listSong", "Перешёл к списку произведений");
        return modelAndView;
    }

    @GetMapping("/addSongForm")
    public ModelAndView addStudentForm() {
        ModelAndView modelAndView = new ModelAndView("add-song-form");
        Song song = new Song();
        modelAndView.addObject("song", song);
        makeLogMessageService.makeMessage("/addSongForm", "Открыл форму добавления нового музыкального произведения");
        return modelAndView;
    }

    @PostMapping("/saveSong")
    public String saveStudent(@ModelAttribute Song song) {
        songRepository.save(song);
        makeLogMessageService.makeMessage("/saveSong", "Добавил или обновил данные музыкального произведения "+song.getName());
        return "redirect:/listSong";
    }

    @GetMapping("/showUpdateFormSong")
    public ModelAndView showUpdateForm(@RequestParam Long songId) {
        ModelAndView modelAndView = new ModelAndView("add-song-form");
        Optional<Song> optionalSong = songRepository.findById(songId);
        Song song = new Song();
        if (optionalSong.isPresent()) {
            song = optionalSong.get();
        }
        modelAndView.addObject("song", song);
        makeLogMessageService.makeMessage("/showUpdateFormSong", "Открыл форму обновления музыкального произведения "+song.getName());
        return modelAndView;
    }

    @GetMapping("/deleteSong")
    public String deleteSong(@RequestParam Long songId) {
        songRepository.deleteById(songId);
        makeLogMessageService.makeMessage("/deleteSong", "Удалил музыкальное произведение с идентификатором "+songId);
        return "redirect:/listSong";
    }
}
