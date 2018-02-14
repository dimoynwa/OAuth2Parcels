-- used in tests that use HSQL
--create table oauth_client_details (
--  client_id VARCHAR(256) PRIMARY KEY,
--  resource_ids VARCHAR(256),
--  client_secret VARCHAR(256),
--  scope VARCHAR(256),
--  authorized_grant_types VARCHAR(256),
--  web_server_redirect_uri VARCHAR(256),
--  authorities VARCHAR(256),
--  access_token_validity INTEGER,
--  refresh_token_validity INTEGER,
--  additional_information VARCHAR(4096),
--  autoapprove VARCHAR(256)
--);

create table IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BYTEA,
  refresh_token VARCHAR(256)
);

create table IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication BYTEA
);

create table IF NOT EXISTS oauth_code (
  code VARCHAR(256), authentication BYTEA
);

create table IF NOT EXISTS oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table IF NOT EXISTS ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);

--INSERT INTO public.users(
--	id, age, email, enabled, name, password, role)
--	VALUES (15000, 2, 'asd', true, 'dimo', 'password', 'ROLE_ADMIN');