package com.capstone.report;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capstone.report.controller.ReportController;
import com.capstone.report.entity.Reports;
import com.capstone.report.entity.Response;
import com.capstone.report.repository.ReportRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class ReportApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	private ReportRepo reportRepo;

	@InjectMocks
	private ReportController reportController;

	private JacksonTester<Response> jsonReport;
	private JacksonTester<Response> jsonReports;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(reportController).build();
	}

	@Test
	public void canAddReport() throws Exception {
		Reports report = new Reports( 1, "same", "assss");

		when(reportRepo.save(report)).thenReturn(report);

		mvc.perform(MockMvcRequestBuilders
				.post("/report/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonReport
						.write(new Response(true, report, "Report added successfully"))
						.getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void canUpdateAReport() throws Exception {

		Reports report1 = new Reports( 2, "gill", "true");
		when(reportRepo.save(report1)).thenReturn(report1);

		mvc.perform(MockMvcRequestBuilders
				.post("/report/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonReport.write(new Response(true, report1, "Report updated successfully"))
				.getJson()))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void canDeleteReport() throws Exception {

		long id = 1;
		doNothing().when(reportRepo).deleteById(id);
		mvc.perform(MockMvcRequestBuilders.delete("/report/delete/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void canGetAllReport() throws Exception {
		Reports report1 = new Reports( 1, "same", "assss");
		Reports report2 = new Reports( 1, "same", "assss");

		List<Reports> reportList = new ArrayList<>();
		reportList.add(report1);
		reportList.add(report2);

		when(reportRepo.findAll()).thenReturn(reportList);

		mvc.perform(MockMvcRequestBuilders
				.get("/report/list")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonReports.write(new Response(true, reportList, "Reports fetched successfully")).getJson()));
	}

	@Test
	public void canGetById() throws Exception {
		Reports report1 = new Reports( 1, "same", "assss");

		when(reportRepo.findById(1L)).thenReturn(Optional.of(report1));
		
		mvc.perform(MockMvcRequestBuilders
				.get("/report/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonReports.write(new Response(true, report1, "Reports fetched successfully")).getJson()));

	}

	@Test 
	public void canGetByPatientId() throws Exception {
		Reports report1 = new Reports( 53, "same", "assss");

		when(reportRepo.getByPatientId(53L)).thenReturn(report1);

		mvc.perform(MockMvcRequestBuilders
				.get("/report/patientId/53")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonReports.write(new Response(true, report1, "Report fetched successfully")).getJson()));

	}
}
