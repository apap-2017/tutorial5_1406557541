package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

@Mapper
public interface CourseMapper {
	@Select("select id_course, name, credits from course where id_course = #{id_course}")
	@Results(value = {
			@Result(property = "id_course", column = "id_course"),
			@Result(property = "name", column = "name"),
			@Result(property = "credits", column = "credits"),
			@Result(property = "students", column = "id_course", javaType = List.class, many = @Many(select = "selectStudents")) })
	CourseModel selectCourse(@Param("id_course") String id_course);
	
	/*
	 * Melakukan join antara tabel studentcourse dengan course berdasarkan
	 * id_course dan difilter hanya pada student dengan NPM yang diberikan.
	 */
	@Select("select student.npm, name, gpa "
	 +"from studentcourse join student "
	 + "on studentcourse.npm = student.npm "
	 + "where studentcourse.id_course = #{id_course}")
	List<StudentModel> selectStudents(@Param("id_course") String id_course);
}

/*@RequestMapping("/course/view")
public String viewCourse(Model model, @RequestParam(value = "id_course", required = false) String id_course) {
	CourseModel course = studentDAO.selectCourse(id_course);

	if (course != null) {
		model.addAttribute("course", course);
		return "view-course";
	} else {
		model.addAttribute("id_course", id_course);
		return "not-found";
	}
}

@RequestMapping("/course/view/{id_course}")
public String viewCoursePath(Model model, @PathVariable(value = "id_course") String id_course) {
	CourseModel course = studentDAO.selectCourse(id_course);
	
	if (course != null) {
		model.addAttribute("course", course);
		return "view-course";
	} else {
		model.addAttribute("id_course", id_course);
		return "not-found";
	}
}*/