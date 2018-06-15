package springBootDemo.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import springBootDemo.model.Department;

@CacheConfig(cacheNames = "department")
@Service
public class DepartmentService {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Resource
	private CacheManager cacheManager;
	private Cache cache;
	
	/**
	 * @param department
	 * @return
	 * 注解和cacheManager 使用一个即可
	 *
	 */
	@CachePut(key = "#department.id")
	public Department save(Department department) {
		this.getClass();
		LOGGER.info("保存 id=" + department.getId() + " 的数据");
//		cache = cacheManager.getCache("department");
//		cache.put(department.getId(),department);
		return department;
	}

	/**
	 * 注解和cacheManager 使用一个即可
	 * @param department
	 * @return
	 */
	@CachePut(key = "#department.id")
	public Department update(Department department) {
		LOGGER.info("修改 id=" + department.getId() + " 的数据");
		cache = cacheManager.getCache("department");
		cache.put(department.getId(),department);
		return department;
	}

//	@Cacheable(key = "#id")
	public Department getDepartmentById(Integer id) {
		LOGGER.info("获取 id=" + id + " 的数据");
		cache = cacheManager.getCache("department");
		Department department = cache.get(id,Department.class);
		return department;
	}
	/**
	 * 注解和cacheManager 使用一个即可
	 * @param id
	 */
//	@CacheEvict(key = "#id")
	public void delete(Integer id) {
		cache = cacheManager.getCache("department");
		cache.evict(id);;
		LOGGER.info("删除 id=" + id + " 的数据");
	}
}
