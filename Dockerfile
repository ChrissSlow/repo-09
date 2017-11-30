FROM node:6.11.2

WORKDIR /usr/src/app

# Copy server-files into working directory and run eslint
COPY ./server /usr/src/app
RUN npm install -g eslint@3.7.1
RUN eslint .

# Copy client-files into working directory and run eslint
COPY ./client /usr/src/app/client
RUN eslint .

# Start building image
FROM node:6.11.2

WORKDIR /usr/src/app
ENV RUNNING_VIA_DOCKER=true
EXPOSE 3000

# Copy package.json and install all dependencies
COPY ./server/package.json /usr/src/app
RUN npm install --production

# Copy source code
COPY ./server /usr/src/app/

# Create directory for runtime data and the needed files
RUN mkdir /usr/src/app/data/runtimedata /usr/src/app/data/runtimedata/clientdata /usr/src/app/data/runtimedata/logs
RUN touch /usr/src/app/data/runtimedata/clientdata/jenkinsJobs.json /usr/src/app/data/runtimedata/clientdata/weatherForecast.json /usr/src/app/data/runtimedata/clientdata/websiteFeed.json /usr/src/app/data/runtimedata/logs/server.log

# Copy source code
COPY ./client /usr/src/app/public

CMD ["npm", "start"]