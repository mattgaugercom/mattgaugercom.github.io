module.exports = function(grunt) {
  grunt.initConfig({
    sass: {
      dev: {
        files: {
          // 'destination': 'source'
          'assets/css/main.css': 'sass/*.scss'
        },
        options: {
          sourceMap: true
        }
      }
    },
    watch: {
      sass: {
        files: 'sass/**/*.scss',
        tasks: ['sass'],
        options: {
        }
      }
    }
  });

  grunt.registerTask('default', ['sass']);

  grunt.loadNpmTasks('grunt-sass');
  grunt.loadNpmTasks('grunt-contrib-watch');
};
