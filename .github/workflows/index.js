#!/usr/bin/env node
const { Octokit } = require('@octokit/rest');

async function createRelease(token, owner, repo, tagName, releaseName) {
  const octokit = new Octokit({ auth: token });

  try {
    const response = await octokit.repos.createRelease({
      owner,
      repo,
      tag_name: tagName,
      name: releaseName,
      body: 'Notas de la versión',
    });

    console.log('Release creado:', response.data.html_url);
  } catch (error) {
    console.error('Error al crear el release:', error.message);
    process.exit(1);
  }
}

const args = process.argv.slice(2);

if (args.length !== 5) {
  console.error('Error: Debes proporcionar el token, propietario del repositorio, nombre del repositorio, nombre de la etiqueta y nombre del release.');
  process.exit(1);
}

createRelease(...args);
