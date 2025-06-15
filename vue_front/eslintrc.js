module.exports = {
  root: true,
  env: {
    node: true
  },
  plugins: [
    'vue'
  ],
  rules: {
    'vue/script-setup-uses-vars': 'error'
  },
  globals: {
    defineProps: 'readonly',
    defineEmits: 'readonly',
    defineExpose: 'readonly',
    withDefaults: 'readonly'
  }
}