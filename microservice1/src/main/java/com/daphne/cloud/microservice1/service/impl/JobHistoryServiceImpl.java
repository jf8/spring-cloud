package com.daphne.cloud.microservice1.service.impl;

import com.daphne.cloud.microservice1.service.JobHistoryService;
import com.daphne.cloud.microservice1.domain.JobHistory;
import com.daphne.cloud.microservice1.repository.JobHistoryRepository;
import com.daphne.cloud.microservice1.repository.search.JobHistorySearchRepository;
import com.daphne.cloud.microservice1.service.dto.JobHistoryDTO;
import com.daphne.cloud.microservice1.service.mapper.JobHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing JobHistory.
 */
@Service
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService{

    private final Logger log = LoggerFactory.getLogger(JobHistoryServiceImpl.class);
    
    private final JobHistoryRepository jobHistoryRepository;

    private final JobHistoryMapper jobHistoryMapper;

    private final JobHistorySearchRepository jobHistorySearchRepository;

    public JobHistoryServiceImpl(JobHistoryRepository jobHistoryRepository, JobHistoryMapper jobHistoryMapper, JobHistorySearchRepository jobHistorySearchRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
        this.jobHistoryMapper = jobHistoryMapper;
        this.jobHistorySearchRepository = jobHistorySearchRepository;
    }

    /**
     * Save a jobHistory.
     *
     * @param jobHistoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobHistoryDTO save(JobHistoryDTO jobHistoryDTO) {
        log.debug("Request to save JobHistory : {}", jobHistoryDTO);
        JobHistory jobHistory = jobHistoryMapper.jobHistoryDTOToJobHistory(jobHistoryDTO);
        jobHistory = jobHistoryRepository.save(jobHistory);
        JobHistoryDTO result = jobHistoryMapper.jobHistoryToJobHistoryDTO(jobHistory);
        jobHistorySearchRepository.save(jobHistory);
        return result;
    }

    /**
     *  Get all the jobHistories.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobHistories");
        Page<JobHistory> result = jobHistoryRepository.findAll(pageable);
        return result.map(jobHistory -> jobHistoryMapper.jobHistoryToJobHistoryDTO(jobHistory));
    }

    /**
     *  Get one jobHistory by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public JobHistoryDTO findOne(Long id) {
        log.debug("Request to get JobHistory : {}", id);
        JobHistory jobHistory = jobHistoryRepository.findOne(id);
        JobHistoryDTO jobHistoryDTO = jobHistoryMapper.jobHistoryToJobHistoryDTO(jobHistory);
        return jobHistoryDTO;
    }

    /**
     *  Delete the  jobHistory by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobHistory : {}", id);
        jobHistoryRepository.delete(id);
        jobHistorySearchRepository.delete(id);
    }

    /**
     * Search for the jobHistory corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobHistoryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of JobHistories for query {}", query);
        Page<JobHistory> result = jobHistorySearchRepository.search(queryStringQuery(query), pageable);
        return result.map(jobHistory -> jobHistoryMapper.jobHistoryToJobHistoryDTO(jobHistory));
    }
}
