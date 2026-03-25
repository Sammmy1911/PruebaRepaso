INSERT INTO drones (id, code, name, status) VALUES
                                                (1, 'DRN-001', 'Falcon One', 'AVAILABLE'),
                                                (2, 'DRN-002', 'Eagle Wing', 'BUSY'),
                                                (3, 'DRN-003', 'Sky Runner', 'MAINTENANCE'),
                                                (4, 'DRN-004', 'Hawk Eye', 'BUSY'),
                                                (5, 'DRN-005', 'Nimbus Jet', 'AVAILABLE');







INSERT INTO deliveries (id, tracking_code, status, destination, assigned_at, drone_id) VALUES
                                                                                           (1, 'DEL-2026-0001', 'COMPLETED', 'Calle 10 # 20-30', '2026-03-13 08:00:00', 1),
                                                                                           (2, 'DEL-2026-0002', 'IN_PROGRESS', 'Avenida 6N # 14-22', '2026-03-13 09:10:00', 2),
                                                                                           (3, 'DEL-2026-0003', 'COMPLETED', 'Cra 80 # 5-11', '2026-03-13 10:15:00', 1),
                                                                                           (4, 'DEL-2026-0004', 'PENDING', 'Calle 45 # 30-18', '2026-03-13 11:20:00', 2),
                                                                                           (5, 'DEL-2026-0005', 'COMPLETED', 'Carrera 56 # 8-40', '2026-03-13 12:05:00', 4),
                                                                                           (6, 'DEL-2026-0006', 'IN_PROGRESS', 'Calle 70 # 12-90', '2026-03-13 12:40:00', 4),
                                                                                           (7, 'DEL-2026-0007', 'PENDING', 'Avenida 3 # 22-15', '2026-03-13 13:00:00', 5),
                                                                                           (8, 'DEL-2026-0008', 'COMPLETED', 'Calle 16 # 101-09', '2026-03-13 13:25:00', 1);

INSERT INTO drone_events (id, event_type, event_time, description, delivery_id, drone_id) VALUES
                                                                                              (1, 'MISSION_STARTED', '2026-03-13 08:01:00', 'Mission started for delivery 1', 1, 1),
                                                                                              (2, 'PACKAGE_PICKED', '2026-03-13 08:04:00', 'Package picked at warehouse', 1,2),
                                                                                              (3, 'ARRIVED_DESTINATION', '2026-03-13 08:18:00', 'Arrived at destination point', 1,3),
                                                                                              (4, 'DELIVERY_COMPLETED', '2026-03-13 08:20:00', 'Delivery completed successfully', 1,4),
                                                                                              (5, 'MISSION_STARTED', '2026-03-13 09:12:00', 'Mission started for delivery 2', 2,5),
                                                                                              (6, 'PACKAGE_PICKED', '2026-03-13 09:16:00', 'Package picked for delivery 2', 2,1),
                                                                                              (7, 'ARRIVED_DESTINATION', '2026-03-13 09:48:00', 'Drone near customer location', 2,1),
                                                                                              (8, 'MISSION_STARTED', '2026-03-13 10:17:00', 'Mission started for delivery 3', 3,2),
                                                                                              (9, 'PACKAGE_PICKED', '2026-03-13 10:20:00', 'Package loaded in drone', 3,2),
                                                                                              (10, 'DELIVERY_COMPLETED', '2026-03-13 10:45:00', 'Delivery 3 completed', 3,3),
                                                                                              (11, 'MISSION_PLANNED', '2026-03-13 11:21:00', 'Pending delivery scheduled', 4,3),
                                                                                              (12, 'MISSION_STARTED', '2026-03-13 12:07:00', 'Mission started for delivery 5', 5,4),
                                                                                              (13, 'PACKAGE_PICKED', '2026-03-13 12:11:00', 'Package picked for delivery 5', 5,4),
                                                                                              (14, 'ARRIVED_DESTINATION', '2026-03-13 12:38:00', 'Arrived to destination for delivery 5', 5,5),
                                                                                              (15, 'DELIVERY_COMPLETED', '2026-03-13 12:41:00', 'Delivery 5 completed', 5,5),
                                                                                              (16, 'MISSION_STARTED', '2026-03-13 12:42:00', 'Mission started for delivery 6', 6,1),
                                                                                              (17, 'PACKAGE_PICKED', '2026-03-13 12:46:00', 'Package picked for delivery 6', 6,2),
                                                                                              (18, 'MISSION_PLANNED', '2026-03-13 13:01:00', 'Delivery 7 queued for assignment', 7,3),
                                                                                              (19, 'MISSION_STARTED', '2026-03-13 13:27:00', 'Mission started for delivery 8', 8,4),
                                                                                              (20, 'PACKAGE_PICKED', '2026-03-13 13:30:00', 'Package secured for delivery 8', 8,5),
                                                                                              (21, 'ARRIVED_DESTINATION', '2026-03-13 13:48:00', 'Reached destination for delivery 8', 8,1),
                                                                                              (22, 'DELIVERY_COMPLETED', '2026-03-13 13:51:00', 'Delivery 8 completed', 8,2);

INSERT INTO drone_positions (id, latitude, longitude, recorded_at, drone_id) VALUES
                                                                                 (1, 3.451620, -76.531980, '2026-03-13 08:05:00', 1),
                                                                                 (2, 3.452410, -76.531120, '2026-03-13 08:10:00', 1),
                                                                                 (3, 3.453300, -76.529800, '2026-03-13 08:15:00', 1),
                                                                                 (4, 3.454150, -76.528760, '2026-03-13 08:20:00', 1),
                                                                                 (5, 3.455020, -76.527940, '2026-03-13 08:25:00', 1),
                                                                                 (6, 3.455830, -76.526880, '2026-03-13 08:30:00', 1),
                                                                                 (7, 3.456420, -76.525910, '2026-03-13 08:35:00', 1),
                                                                                 (8, 3.456910, -76.524900, '2026-03-13 08:40:00', 1),
                                                                                 (9, 3.457300, -76.523950, '2026-03-13 08:45:00', 1),
                                                                                 (10, 3.457880, -76.522970, '2026-03-13 08:50:00', 1),
                                                                                 (11, 3.458200, -76.522100, '2026-03-13 08:55:00', 1),
                                                                                 (12, 3.421100, -76.544200, '2026-03-13 09:20:00', 2),
                                                                                 (13, 3.422000, -76.543100, '2026-03-13 09:26:00', 2),
                                                                                 (14, 3.422950, -76.542200, '2026-03-13 09:32:00', 2),
                                                                                 (15, 3.423770, -76.541300, '2026-03-13 09:38:00', 2),
                                                                                 (16, 3.424500, -76.540500, '2026-03-13 09:44:00', 2),
                                                                                 (17, 3.425100, -76.539900, '2026-03-13 09:50:00', 2),
                                                                                 (18, 3.439800, -76.520200, '2026-03-13 10:40:00', 3),
                                                                                 (19, 3.440250, -76.519950, '2026-03-13 10:48:00', 3),
                                                                                 (20, 3.440700, -76.519650, '2026-03-13 10:56:00', 3),
                                                                                 (21, 3.441080, -76.519200, '2026-03-13 11:04:00', 3),
                                                                                 (22, 3.441350, -76.518850, '2026-03-13 11:12:00', 3),
                                                                                 (23, 3.467100, -76.535500, '2026-03-13 12:10:00', 4),
                                                                                 (24, 3.467850, -76.534700, '2026-03-13 12:15:00', 4),
                                                                                 (25, 3.468600, -76.533900, '2026-03-13 12:20:00', 4),
                                                                                 (26, 3.469150, -76.533050, '2026-03-13 12:25:00', 4),
                                                                                 (27, 3.469720, -76.532300, '2026-03-13 12:30:00', 4),
                                                                                 (28, 3.470300, -76.531600, '2026-03-13 12:35:00', 4),
                                                                                 (29, 3.470900, -76.530900, '2026-03-13 12:40:00', 4),
                                                                                 (30, 3.471420, -76.530220, '2026-03-13 12:45:00', 4),
                                                                                 (31, 3.472000, -76.529500, '2026-03-13 12:50:00', 4),
                                                                                 (32, 3.473000, -76.545000, '2026-03-13 13:05:00', 5),
                                                                                 (33, 3.472450, -76.544250, '2026-03-13 13:12:00', 5),
                                                                                 (34, 3.471900, -76.543600, '2026-03-13 13:19:00', 5),
                                                                                 (35, 3.471250, -76.543000, '2026-03-13 13:26:00', 5),
                                                                                 (36, 3.470700, -76.542500, '2026-03-13 13:33:00', 5);


