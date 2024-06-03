#!/usr/bin/env node
const { Octokit } = require('@octokit/rest');

async function createRelease(owner, repo, tagName, releaseName) {
  const token = process.env.GITHUB_TOKEN;

  if (!token) {
    console.error('Error: You must provide an authentication token in the GITHUB_TOKEN environment variable.');
    process.exit(1);
  }

  const octokit = new Octokit({ auth: token });

  try {
    const response = await octokit.repos.createRelease({
      owner,
      repo,
      tag_name: tagName,
      name: releaseName,
      body: 'release notes',
    });

    console.log('release created:', response.data.html_url);
  } catch (error) {
    console.error('Error creating release:', error.message);
    process.exit(1);
  }
}

const args= process.argv.slice(2);

if (args.length !== 4) {
  console.error('Error: You must provide the repository owner, repository name, tag name, and release name.');
  process.exit(1);
}

createRelease(...args);
