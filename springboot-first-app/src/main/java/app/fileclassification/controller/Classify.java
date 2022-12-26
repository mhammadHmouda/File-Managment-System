package app.fileclassification.controller;

import app.exception.ClassifyMethodNotFound;
import app.fileclassification.request.ClassifyDateRequest;
import app.fileclassification.service.factory.FactoryClassify;
import app.response.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static app.fileclassification.constant.ClassifyEndPoint.*;
import static app.fileclassification.constant.ClassifyEnum.*;

@RestController
public class Classify {

    private static final Logger logger = LoggerFactory.getLogger(Classify.class);

    @Autowired
    FactoryClassify<ClassifyDateRequest> classifyDateFactory;

    @Autowired
    FactoryClassify<String> classifyNameFactory;

    @Autowired
    FactoryClassify<String> classifyTypeFactory;

    @Autowired
    FactoryClassify<Integer> classifySizeFactory;

    @GetMapping(CLASSIFY_BY_TYPE_END_POINT)
    public ResponseMessage classifyByType(@PathVariable String type, HttpServletRequest request) throws ClassifyMethodNotFound {
        logger.info("Create classifyByType response");
        return classifyTypeFactory.factory(type, CLASSIFY_OF_TYPE.name(), request);
    }

    @GetMapping(CLASSIFY_BY_SIZE_END_POINT)
    public ResponseMessage classifyBySize(@PathVariable Integer size, HttpServletRequest request) throws ClassifyMethodNotFound {
        logger.info("Create classifyBySize response");
        return classifySizeFactory.factory(size, CLASSIFY_OF_SIZE.name(), request);
    }

    @GetMapping(CLASSIFY_BY_DATE_END_POINT)
    public ResponseMessage classifyByDate(@RequestBody ClassifyDateRequest classifyDateRequest, HttpServletRequest request) throws ClassifyMethodNotFound {
        logger.info("Create classifyByDate response");
        return classifyDateFactory.factory(classifyDateRequest, CLASSIFY_OF_DATE.name(), request);
    }

    @GetMapping(CLASSIFY_BY_NAME_END_POINT)
    public ResponseMessage classifyByName(@PathVariable String fileName, HttpServletRequest request) throws ClassifyMethodNotFound {
        logger.info("Create classifyByDate response");
        return classifyNameFactory.factory(fileName, CLASSIFY_OF_NAME.name(), request);
    }
}
