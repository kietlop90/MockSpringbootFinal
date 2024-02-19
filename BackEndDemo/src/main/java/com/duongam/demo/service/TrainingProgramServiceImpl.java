package com.duongam.demo.service;

import com.duongam.demo.dto.request.forupdate.URequestTrainingProgram;
import com.duongam.demo.dto.response.fordetail.*;
import com.duongam.demo.entities.*;
import com.duongam.demo.entities.Class;
import com.duongam.demo.repositories.*;
import com.duongam.demo.service.template.ITrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrainingProgramServiceImpl implements ITrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private TrainingProgramSyllabusRepository trainingProgramSyllabusRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SyllabusRepository syllabusRepository;

    @Autowired
    private TrainingUnitRepository trainingUnitRepository;

    @Autowired
    private TrainingContentRepository trainingContentRepository;

    private List<String> listSearch = new ArrayList<>();

    @Override
    @Transactional
    public DResponseSyllabus getSylabusByCode(String code) {
        Syllabus syllabus = syllabusRepository.findByTopicCode(code).orElse(null);
        if (syllabus != null) {
            return new DResponseSyllabus(syllabus);
        }
        return null;
    }


    @Override
    @Transactional
    public List<DResponseClass> getALlClassOfTrainingProgram(String code) {
        List<Class> classList = classRepository.getALlClassByTrainingProgramCode(code);
        return classList.stream()
                .map(value -> {
                    return new DResponseClass(value);
                }).collect(Collectors.toList());
    }


    @Override
    public List<DResponseSyllabus> getAllSyllabusByTrainingProgramCode(String code) {
        List<Syllabus> syllabusList = trainingProgramSyllabusRepository.getAllSyllabusCodesByTrainingProgramCode(code);
        return syllabusList.stream().map(value -> {
            DResponseSyllabus dResponseSyllabus = new DResponseSyllabus();
            dResponseSyllabus.setTopicCode(value.getTopicCode());
            dResponseSyllabus.setStatus(value.getStatus());
            dResponseSyllabus.setCreatedBy(value.getCreatedBy());
            dResponseSyllabus.setVersion(value.getVersion());
            dResponseSyllabus.setTrainingMaterials(value.getTrainingMaterials());
            dResponseSyllabus.setTechnicalGroup(value.getTechnicalGroup());
            dResponseSyllabus.setTopicName(value.getTopicName());
            dResponseSyllabus.setTopicOutline(value.getTopicOutline());
            dResponseSyllabus.setTrainingPrinciples(value.getTrainingPrinciples());
            dResponseSyllabus.setCreatedDate(value.getCreatedDate());
            dResponseSyllabus.setModifiedBy(value.getModifiedBy());
            return dResponseSyllabus;
        }).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public List<DReponseTrainingProgram> listAllTrainingPrograms() {
        List<TrainingProgram> trainingProgramList = trainingProgramRepository.getAllBy();
        return trainingProgramList.stream()
                .map(value -> {
                    return new DReponseTrainingProgram(value);
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional

    public DReponseTrainingProgram deleteTrainingProgramById(String id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findByCode(id).orElse(null);
        assert trainingProgram != null : "Training program not found";
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

            trainingProgram1.setCreatedBy(trainingProgram.getCreatedBy());

//            trainingProgram1.setCreateBy(trainingProgram.getCreateBy());

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
        assert trainingProgram != null;
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
        return new DReponseTrainingProgram(trainingProgram);
    }

    @Override
    public List<DReponseTrainingUnit> findALlUnit(String code) {
        List<TrainingUnit> trainingUnitList = trainingUnitRepository.findALlTrainingUnitBySyllabusCode(code);
        return trainingUnitList.stream().map(value -> {
            DReponseTrainingUnit dReponseTrainingUnit = new DReponseTrainingUnit();
            dReponseTrainingUnit.setDayNumber("Days " + value.getDayNumber());
            dReponseTrainingUnit.setName(value.getName());

            List<TrainingContent> trainingContentList = trainingContentRepository.findALlTrainingContentByTrainingUnitId(value.getUnitCode());
            List<DReponseTrainingContent> dReponseTrainingContentList = trainingContentList.stream().map(value1 -> {
                DReponseTrainingContent dReponseTrainingContent = new DReponseTrainingContent();
                dReponseTrainingContent.setTrainingFormat(value1.getTrainingFormat());
                dReponseTrainingContent.setDuration(value1.getDuration() + " mins");
                dReponseTrainingContent.setNote(value1.getNote());
                dReponseTrainingContent.setDeliveryType(value1.getDeliveryType());
                return dReponseTrainingContent;
            }).collect(Collectors.toList());

            dReponseTrainingUnit.setDReponseTrainingContentList(dReponseTrainingContentList);
            return dReponseTrainingUnit;
        }).collect(Collectors.toList());
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
    public void removeTagAndSearch(String tagToRemove) {
        listSearch.remove(tagToRemove);
    }

    @Override
    @Transactional
    public List<String> getListSearch() {
        return listSearch;
    }


    @Override
    @Transactional
    public List<DReponseTrainingProgram> searchALlTrainingProgram(String name) {

//    @Override
//    @Transactional
//    public List<DReponseTrainingProgram> searchALlTrainingProgram(String name) {
//        List<TrainingProgram> trainingProgramList = trainingProgramRepository.findByNameLike(name);
//        return trainingProgramList.stream().map(value -> {
//            return new DReponseTrainingProgram(value);
//        }).collect(Collectors.toList());
//    }


        if (Objects.equals(name, "asdfghjkl")) {
            name = "";
        } else {
            for (String tag : listSearch) {
                if (tag.contains(name)) {
                    name = "";
                    break;
                }
            }
        }

        if (!Objects.equals(name, "")) {
            listSearch.add(name);
        }

        if (listSearch.isEmpty()) {
            return listAllTrainingPrograms();
        }

        if (listSearch.size() > 4) {
            listSearch.remove(name);
        }

        List<TrainingProgram> trainingProgramList;

        switch (listSearch.size()) {
            case 1:
                trainingProgramList = trainingProgramRepository.findByNameLike1tag(listSearch.get(0));
                break;
            case 2:
                trainingProgramList = trainingProgramRepository.findBy2Tags(listSearch.get(0), listSearch.get(1));
                break;
            case 3:
                trainingProgramList = trainingProgramRepository.findBy3Tags(listSearch.get(0), listSearch.get(1), listSearch.get(2));
                break;
            case 4:
                try {
                    Integer durationForSearch = Integer.parseInt(listSearch.get(3));
                    trainingProgramList = trainingProgramRepository.findBy4Tags(
                            listSearch.get(0), listSearch.get(1), listSearch.get(2), durationForSearch);
                } catch (NumberFormatException e) {
                    // Handle the case when the duration is not a valid integer
                    e.printStackTrace();
                    return Collections.emptyList();
                }
                break;
            default:
                return Collections.emptyList();
        }

        return trainingProgramList.stream()
                .map(DReponseTrainingProgram::new)
                .collect(Collectors.toList());
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
