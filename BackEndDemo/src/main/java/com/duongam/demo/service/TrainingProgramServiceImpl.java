package com.duongam.demo.service;

import com.duongam.demo.dto.request.forupdate.URequestTrainingProgram;
import com.duongam.demo.dto.response.fordetail.DReponseTrainingProgram;
import com.duongam.demo.entities.TrainingProgram;
import com.duongam.demo.entities.User;
import com.duongam.demo.repositories.TrainingProgramRepository;
import com.duongam.demo.repositories.UserRepository;
import com.duongam.demo.service.template.ITrainingProgramService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TrainingProgramServiceImpl implements ITrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<DReponseTrainingProgram> listAllTrainingPrograms() {
        List<TrainingProgram> trainingProgramList = trainingProgramRepository.getAllBy();
        return trainingProgramList.stream()
                .map(value -> {
                    DReponseTrainingProgram reponseTrainingProgram = new DReponseTrainingProgram(value);
                    return reponseTrainingProgram;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional

    public DReponseTrainingProgram deleteTrainingProgramById(String id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(id).orElse(null);
        assert trainingProgram != null;
        DReponseTrainingProgram reponseTrainingProgram = new DReponseTrainingProgram(trainingProgram);
        trainingProgramRepository.deleteById(id);
        return reponseTrainingProgram;
    }


    @Override
    @Transactional
    public DReponseTrainingProgram duplicateTrainingProgram(String id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(id).orElse(null);

        if (trainingProgram != null) {
            TrainingProgram trainingProgram1 = new TrainingProgram();
            trainingProgram1.setCode(generateRandomLetter(trainingProgram.getCode()));
            trainingProgram1.setName(trainingProgram.getName());
            trainingProgram1.setDuration(trainingProgram.getDuration());
            trainingProgram1.setStatus(trainingProgram.getStatus());
            trainingProgram1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            trainingProgram1.setCreateBy(trainingProgram.getCreateBy());
            trainingProgramRepository.save(trainingProgram1);

            return new DReponseTrainingProgram(trainingProgram1);
        } else {
            // Handle the case when the training program is not found
            return null;
        }
    }

    @Override
    @Transactional
    public DReponseTrainingProgram deActiveTrainingProgram(String id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(id).orElse(null);
        assert trainingProgram != null : "trainingProgram must not be found";
        if (trainingProgram.getStatus() == 1) {
            trainingProgram.setStatus(2);
        } else {
            trainingProgram.setStatus(1);
        }
        trainingProgram.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        trainingProgramRepository.save(trainingProgram);
        return new DReponseTrainingProgram(trainingProgram);
    }

    @Override
    @Transactional
    public DReponseTrainingProgram findTrainingProgrammById(String id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(id).orElse(null);
        DReponseTrainingProgram reponseTrainingProgram = new DReponseTrainingProgram(trainingProgram);
        return reponseTrainingProgram;
    }

    private String generateRandomLetter(String input) {
        if (input != null && !input.isEmpty()) {
            char randomLetter1 = (char) ('A' + (int) (Math.random() * 26));
            char randomLetter2 = (char) ('A' + (int) (Math.random() * 26));
            return input + " -> Copy" + randomLetter1 + randomLetter2;
        } else {
            // Handle the case when the input is empty or null
            return input;
        }
    }


    @Override
    @Transactional
    public List<DReponseTrainingProgram> searchALlTrainingProgram(String name) {
        List<TrainingProgram> trainingProgramList = trainingProgramRepository.findByNameLike(name);
        return trainingProgramList.stream().map(value -> {
            return new DReponseTrainingProgram(value);
        }).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public DReponseTrainingProgram updateTrainingProgramById(URequestTrainingProgram requestTrainingProgramUpdate) {
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(requestTrainingProgramUpdate.getId()).orElse(null);

        if (trainingProgram != null) {
            trainingProgram.setName(requestTrainingProgramUpdate.getProgramName());
            int numberPart = extractNumberBeforeDays(requestTrainingProgramUpdate.getDuration());
            trainingProgram.setDuration(numberPart);
            if (Objects.equals(requestTrainingProgramUpdate.getStatus(), "Active")) {
                trainingProgram.setStatus(1);
            } else if ("InActive".equals(requestTrainingProgramUpdate.getStatus())) {
                trainingProgram.setStatus(2);
            } else {
                trainingProgram.setStatus(3);
            }

            trainingProgram.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            trainingProgramRepository.save(trainingProgram);
            return new DReponseTrainingProgram(trainingProgram);
        }
        return null;
    }


    private static int extractNumberBeforeDays(String input) {
        // Tách các từ trong chuỗi
        String[] words = input.split("\\s+");

        // Duyệt qua từng từ để tìm số trước chữ 'days'
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("days") && i > 0) {
                // Nếu từ trước đó có thể chuyển thành số, trả về giá trị đó
                try {
                    return Integer.parseInt(words[i - 1]);
                } catch (NumberFormatException e) {
                    // Xử lý nếu không thể chuyển đổi thành số
                    e.printStackTrace();
                }
            }
        }

        // Trả về giá trị mặc định nếu không tìm thấy
        return 0;
    }
}