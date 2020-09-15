using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace TuDien_WebAPI.Models
{
    public partial class TuDienAnhVietContext : DbContext
    {
        public TuDienAnhVietContext()
        {
        }

        public TuDienAnhVietContext(DbContextOptions<TuDienAnhVietContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Vocabulary> Vocabulary { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Server=DESKTOP-AJN4OBH\\SQLEXPRESS;Database=TuDienAnhViet;Trusted_Connection=True;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Vocabulary>(entity =>
            {
                entity.HasKey(e => e.Matu);

                entity.Property(e => e.Matu)
                    .HasColumnName("matu")
                    .ValueGeneratedNever();

                entity.Property(e => e.Detail).HasColumnName("detail");

                entity.Property(e => e.Word)
                    .HasColumnName("word")
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });
        }
    }
}
