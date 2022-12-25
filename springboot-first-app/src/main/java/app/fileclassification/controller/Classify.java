package app.fileclassification.controller;

import app.fileclassification.request.ClassifyDateRequest;
import app.response.ResponseMessage;
import app.fileclassification.service.abs.AClassify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static app.fileclassification.constant.ClassifyEndPoint.*;
import static app.filerepository.export.service.enums.ExportType.*;

@RestController
public class Classify {

    @Autowired
    @Qualifier("date")
    private AClassify<ClassifyDateRequest> classifyDateService;
    @Autowired
    @Qualifier("type")
    private AClassify<String> classifyTypeService;
    @Autowired
    @Qualifier("size")
    private AClassify<Integer> classifySizeService;
    @Autowired
    @Qualifier("name")
    private AClassify<String> classifyNameService;

    private static final Logger logger = LoggerFactory.getLogger(Classify.class);

    @GetMapping(CLASSIFY_BY_TYPE_END_POINT)
    public ResponseMessage classifyByType(@PathVariable String type) {
        logger.info("Create classifyByType response");
        return classifyTypeService.classify(type, TYPE.name());
    }

    @GetMapping(CLASSIFY_BY_SIZE_END_POINT)
    public ResponseMessage classifyBySize(@PathVariable Integer size){
        logger.info("Create classifyBySize response");
        return classifySizeService.classify(size, SIZE.name());
    }

    @GetMapping(CLASSIFY_BY_DATE_END_POINT)
    public ResponseMessage classifyByDate(@RequestBody ClassifyDateRequest classifyDateRequest){
        logger.info("Create classifyByDate response");
        return classifyDateService.classify(classifyDateRequest, DATE.name());
    }

    @GetMapping(CLASSIFY_BY_NAME_END_POINT)
    public ResponseMessage classifyByName(@PathVariable String fileName){
        logger.info("Create classifyByDate response");
        return classifyNameService.classify(fileName, NAME.name());
    }
}
