terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "3.56.0"
    }
  }
  backend "s3" {
    bucket = "pgr301-2015-terraform"
    key    = "abka007/terraform.state"
    region = "eu-west-1"
  }
}