-- =====================================================
-- M1 三模块管理菜单 (Task/PM/Agent)
-- 执行前请确认 system_menu 表 parent_id 对应正确的菜单根节点
-- =====================================================

-- 任务卡管理
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, visible, keep_alive) VALUES
('任务卡管理', '', 1, 10, 0, '/task', 'ep:list', '', 0, 1, 1);

SET @task_parent = LAST_INSERT_ID();

INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, visible, keep_alive) VALUES
('任务卡查询', 'task:card:query', 2, 1, @task_parent, 'card', NULL, 'task/card/index', 0, 1, 1),
('任务证据', 'task:evidence:query', 2, 2, @task_parent, 'evidence', NULL, 'task/evidence/index', 0, 1, 1);

-- 项目管理
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, visible, keep_alive) VALUES
('项目管理', '', 1, 11, 0, '/pm', 'ep:folder', '', 0, 1, 1);

SET @pm_parent = LAST_INSERT_ID();

INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, visible, keep_alive) VALUES
('项目列表', 'pm:project:query', 2, 1, @pm_parent, 'project', NULL, 'pm/project/index', 0, 1, 1);

-- 智能体管理
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, visible, keep_alive) VALUES
('智能体管理', '', 1, 12, 0, '/agent', 'ep:user', '', 0, 1, 1);

SET @agent_parent = LAST_INSERT_ID();

INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, visible, keep_alive) VALUES
('智能体名录', 'agent:info:query', 2, 1, @agent_parent, 'info', NULL, 'agent/info/index', 0, 1, 1);
