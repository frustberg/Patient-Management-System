package com.frustberg.patientservice.kafka;
import com.frustberg.patientservice.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {

  private static final Logger log = LoggerFactory.getLogger(
      KafkaProducer.class);
  private final KafkaTemplate<String, byte[]> kafkaTemplate;

  public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendEvent(Patient patient) {
    PatientEvent event = PatientEvent.newBuilder()
        .setPatientId(patient.getId().toString())
        .setName(patient.getName())
        .setEmail(patient.getEmail())
        .setEventType("PATIENT_CREATED")
        .build();

    try {
//        log.info("Producing billing event to Kafka for patientId={}", event.getPatientId());
        log.info(
                "Producing Patient Event -> Id={}, Name={}, Email={}, EventType={}",
                event.getPatientId(),
                event.getName(),
                event.getEmail(),
                event.getEventType()
        );

        kafkaTemplate.send("patient", event.toByteArray());
    } catch (Exception e) {
      log.error("Error sending PatientCreated event: {}", event);
    }
  }
}
